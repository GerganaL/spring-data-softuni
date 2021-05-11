package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Rating;
import softuni.exam.models.Seller;
import softuni.exam.models.dto.xmls.SellerImportDto;
import softuni.exam.models.dto.xmls.SellerRootImportDto;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImp implements SellerService {

    private final static String SELLER_PATH = "src/main/resources/files/xml/sellers.xml";

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    @Autowired
    public SellerServiceImp(SellerRepository sellerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(SELLER_PATH)));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        SellerRootImportDto sellerRootImportDto = this.xmlParser.parseXml(SellerRootImportDto.class, SELLER_PATH);

        for (SellerImportDto sellerDto : sellerRootImportDto.getSellerImportDtos()) {
            Rating rating;
            try {
                rating = Rating.valueOf(sellerDto.getRating());
            } catch (Exception e) {
                sb.append("Invalid seller").append(System.lineSeparator());
                continue;
            }
            Optional<Seller> byEmail = this.sellerRepository.findByEmail(sellerDto.getEmail());
            if (validationUtil.isValid(sellerDto) && byEmail.isEmpty()) {
                Seller seller = this.modelMapper.map(sellerDto, Seller.class);
                seller.setRating(rating);
                this.sellerRepository.saveAndFlush(seller);
                sb.append(String.format("Successfully import seller %s - %s", sellerDto.getLastName(), sellerDto.getEmail()));
            } else {
                sb.append("Invalid seller").append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
