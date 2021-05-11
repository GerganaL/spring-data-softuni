package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.Offer;
import softuni.exam.models.Seller;
import softuni.exam.models.dto.xmls.OfferImportDto;
import softuni.exam.models.dto.xmls.OfferRootImportDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

@Service
public class OfferServiceImpl implements OfferService {
    private static final String OFFER_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(OFFER_PATH)));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        OfferRootImportDto offerRootImportDto = this.xmlParser.parseXml(OfferRootImportDto.class, OFFER_PATH);

        for (OfferImportDto offerImportDto : offerRootImportDto.getOfferImportDtos()) {

            if (this.validationUtil.isValid(offerImportDto)) {
                Offer offer = this.modelMapper.map(offerImportDto, Offer.class);

                Car car = this.carRepository.findById(offerImportDto.getCar().getId()).get();
                Seller seller = this.sellerRepository.findById(offerImportDto.getSeller().getId()).get();


                //if has exception with this
               // offer.setPictures(car.getPictures());
                //do this
               offer.setPictures(new HashSet<>(car.getPictures()));
                offer.setCar(car);
                offer.setSeller(seller);

                this.offerRepository.saveAndFlush(offer);
                sb.append(String.format("Successfully import offer %s - %s", offer.getAddedOn(), offer.isHasGoldStatus()));
                sb.append(System.lineSeparator());
            } else {
                sb.append("Invalid offer").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
