import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineShould {
    /*
    PRODUCE_AN_EMPTY_TEMPLATE_WHEN_THERE_IS_NO_TEMPLATE
    null, date = "lunes" -> ""

    PRODUCE_THE_SAME_TEMPLATE_WHEN_THERE_ARE_NO_VARIABLES
    "hola", -> "hola"
    "hola", null -> "hola `$user`"

    PRODUCE_THE_SAME_TEMPLATE_WHEN_VARIABLES_HAVE_INCORRECT_FORMAT
    "hola user", user = "carlos", date = "lunes" -> "hola user"
    "hola `user`", user = "carlos" -> "hola `user`"
    "hola $user", user = "carlos" -> "hola $user"

    PARSE_ANY_VARIABLES_IN_TEMPLATE
    "hola `$user`", user = "carlos" -> "hola carlos"
    "hola `$user`", hoy es dia `$day`", user = "carlos", day = "lunes" -> "hola carlos, hoy es dia lunes"
    "hola `$user2`", user2 = "raul" -> "hola raul"
     */
    @Test
    void produce_an_empty_template_when_there_is_no_template(){
        assertThat(TemplateEngine.parse(null, new HashMap<>())).isEqualTo("");
    }

    @Test
    void produce_the_same_template_when_there_are_no_variables() {
        assertThat(TemplateEngine.parse("hola", new HashMap<>())).isEqualTo("hola");
        assertThat(TemplateEngine.parse("hola", null)).isEqualTo("hola");
    }

    @Test
    void produce_the_same_template_when_variables_in_template_have_incorrect_format(){
        Map<String, String> variables = new HashMap<>();
        variables.put("user", "carlos");
        assertThat(TemplateEngine.parse("hola user", variables)).isEqualTo("hola user");
    }

    @Test
    void parse_any_variables_in_template() {
        Map<String, String> variables = new HashMap<>();
        variables.put("user", "carlos");
        assertThat(TemplateEngine.parse("hola `$user`", variables)).isEqualTo("hola carlos");
        variables.put("day", "lunes");
        assertThat(TemplateEngine.parse("hola `$user`, hoy es dia `$day`", variables)).isEqualTo("hola carlos, hoy es dia lunes");
    }


}

