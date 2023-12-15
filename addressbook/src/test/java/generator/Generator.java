package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import model.GroupData;

import java.util.ArrayList;

import static common.RandomStringGenerator.randomString;

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
    public static void main(String[] args) {
        var generator= new Generator();
        //этот блок анализирует опции командной строки
        // 1 создается парсер командной строки
        params = new Params();
        new JCommander(params).parse(args);
        //метод прописывает в аргументы значения из параметров
        generator.run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private void save(Object data) {
    }

    private Object generate() {
        if ("groups".equals(params.getType())) {
            return generateGroups();
        } else if (("contacts".equals(params.getType()))) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный формат" + params.getType());
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < params.getCount(); i++) {
            result.add(new GroupData()
                    .withName(randomString(i * 10))
                    .withHeader(randomString(i * 10))
                    .withFooter(randomString(i * 10)));
        }
        return result;
    }

    private Object generateContacts() {
        return null;
    }


}
