import java.util.Map;

public class TemplateEngine {
    static String parse(String template, Map<String, String> variables) {
        if(template==null){
            return "";
        }
        if(variables==null){
            return template;
        }
        for (String key : variables.keySet()) {
            String value = variables.get(key);
            if (value != null) {
                template = template.replace("`$" + key + "`", value);
            }
        }
        return template;
    }
}
