import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineShould {
    /*
    "hola", -> "hola"
    "hola `$user`", user = "carlos" -> "hola carlos"
    "hola `$user`, hoy es dia `$day`", user = "carlos", day = "lunes" -> "hola carlos, hoy es dia lunes"
    "hola `$user`, date = "lunes" -> "hola `$user`"
    "hola `$user2`", user2 = "raul" -> "hola raul"
    "hola user, hoy es dia date", user = "carlos", date = "lunes" -> "hola user, hoy es dia date"
    "hola `user`", user = "carlos" -> "hola `user`"
    "hola $user", user = "carlos" -> "hola $user"
    null, date = "lunes" -> ""
    "hola `$user`", null -> "hola `$user`"
     */
    @Test
    void produce_the_same_template_when_there_are_no_variables() {
        assertThat(parse("hola", new HashMap<>())).isEqualTo("hola");
    }

    @Test
    void parse_variables_in_template() {
        Map<String, String> variables = new HashMap<>();
        variables.put("user", "carlos");
        assertThat(parse("hola `$user`", variables)).isEqualTo("hola carlos");
        variables.put("day", "lunes");
        assertThat(parse("hola `$user`, hoy es dia `$day`", variables)).isEqualTo("hola carlos, hoy es dia lunes");
    }

    @Test
    void parse_variables_in_template_2() {
        Map<String, String> variables = new HashMap<>();
        variables.put("user2", "raul");
        assertThat(parse("hola `$user2`", variables)).isEqualTo("hola raul");
    }

    private String parse(String template, Map<String, String> variables) {
        for (String key : variables.keySet()) {
            String value = variables.get(key);
            if (value != null) {
                template = template.replace("`$" + key + "`", value);
            }
        }
        return template;
    }
}

