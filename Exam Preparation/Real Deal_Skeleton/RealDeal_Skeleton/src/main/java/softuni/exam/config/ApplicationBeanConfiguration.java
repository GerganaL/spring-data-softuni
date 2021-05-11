package softuni.exam.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParser;
import softuni.exam.util.XmlParserImpl;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return LocalDateTime
                                .parse(jsonElement.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return LocalDate
                                .parse(jsonElement.getAsString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    }
                })
                .create();
    }

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl(validator());
    }



}
