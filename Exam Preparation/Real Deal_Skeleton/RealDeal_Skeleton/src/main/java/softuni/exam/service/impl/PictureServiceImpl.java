package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Picture;
import softuni.exam.models.dto.jsons.PictureImportDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {
    private final static String PICTURE_PATH = "src/main/resources/files/json/pictures.json";
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, CarRepository carRepository) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
    }


    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PICTURE_PATH)));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();
        PictureImportDto[] pictureImportDtos = this.gson.fromJson(this.readPicturesFromFile(), PictureImportDto[].class);

        for (PictureImportDto pictureDto : pictureImportDtos) {
            //check for unique picture
            Optional<Picture> byName = this.pictureRepository.findByName(pictureDto.getName());
            if(this.validationUtil.isValid(pictureDto) && byName.isEmpty()){
                Picture picture = this.modelMapper.map(pictureDto, Picture.class);
                picture.setCar(this.carRepository.getOne(pictureDto.getCar()));
                this.pictureRepository.saveAndFlush(picture);

                sb.append(String.format("Successfully import picture - %s",pictureDto.getName()));
                sb.append(System.lineSeparator());
            }else {
                sb.append("Invalid picture").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
