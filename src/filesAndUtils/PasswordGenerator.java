package filesAndUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    public static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String DIGITS = "0123456789";
    public static final String ESPECIAL = "!@#$%&*?";
    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;
    private boolean useEspecial;

    private PasswordGenerator(){
        throw new UnsupportedOperationException("Empty constructor is not supported");
    }

    public PasswordGenerator(PasswordGeneratorBuilder builder){
        this.useLower = builder.useLower;
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
        this.useEspecial = builder.useEspecial;
    }

    public static class PasswordGeneratorBuilder{

        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;
        private boolean useEspecial;

        public PasswordGeneratorBuilder(){
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
            this.useEspecial = false;
        }

        public PasswordGeneratorBuilder useLower(boolean useLower){
            this.useLower = useLower;
            return this;
        }

        public PasswordGeneratorBuilder useUpper(boolean useUpper){
            this.useUpper = useUpper;
            return this;
        }

        public PasswordGeneratorBuilder useDigits(boolean useDigits){
            this.useDigits = useDigits;
            return this;
        }

        public PasswordGeneratorBuilder useEspecial(boolean useEspecial){
            this.useEspecial = useEspecial;
            return this;
        }

        public PasswordGenerator build(){
            return new PasswordGenerator(this);
        }
    }

    public String generate(int length){
        if (length <= 0){ return ""; }

        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        List<String> charCategories = new ArrayList<String>(4);
        if(useLower){
            charCategories.add(LOWER);
        }
        if(useUpper){
            charCategories.add(UPPER);
        }
        if(useDigits){
            charCategories.add(DIGITS);
        }
        if(useEspecial){
            charCategories.add(ESPECIAL);
        }

        for (int i = 0; i < length; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return new String(password);
    }

}
