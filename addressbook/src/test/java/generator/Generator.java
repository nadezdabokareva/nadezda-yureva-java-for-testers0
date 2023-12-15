package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.RandomStringGenerator;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static common.RandomStringGenerator.randomString;
import static tests.TestBase.randomFile;

public class Generator {
    static class Params {
        @Parameter(names = {"--type", "-t"})
        private String type;

        @Parameter(names = {"--output", "-o"})
        private String output;

        @Parameter(names = {"--format", "-f"})
        private String format;

        @Parameter(names = {"--count", "-c"})
        private Integer count;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }

    static Params params;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        //этот блок анализирует опции командной строки
        // 1 создается парсер командной строки
        params = new Params();
        new JCommander(params).parse(args);
        //метод прописывает в аргументы значения из параметров
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private void save(Object data) throws IOException {
        if ("json".equals(params.getFormat())) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(params.output)) {
                writer.write(json);
            }

        } if ("yaml".equals(params.getFormat())) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File((params.output)), data);

          } else {
             throw new IllegalArgumentException("Неизвестный формат" + params.getFormat());
    }

}

    private Object generate() {
        if ("groups".equals(params.getType())) {
            return generateGroups();
        } else if (("contacts".equals(params.getType()))) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип" + params.getType());
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < params.getCount(); i++) {
            result.add(new GroupData()
                            .withId("")
                    .withName(randomString(i * 10))
                    .withHeader(randomString(i * 10))
                    .withFooter(randomString(i * 10)));
        }
        return result;
    }

    private Object generateContacts() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < params.getCount(); i++) {
            result.add(new ContactData()
                    .withFirstName(RandomStringGenerator.randomString(i * 10))
                    .withMiddleName(RandomStringGenerator.randomString(i * 10))
                    .withLastName(RandomStringGenerator.randomString(i * 10))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        return result;
    }


}
