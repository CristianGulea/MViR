package ModelTest;

import org.junit.jupiter.api.Test;
import ro.ubb.model.Code;

import static org.junit.jupiter.api.Assertions.*;

public class CodeTest {
    @Test
    public void testCodeConstructor() {
        Code codeCreateWAllArgsConstructor = new Code("value");
        Code codeCreateWNoArgsConstructor = new Code();

        assertEquals("value", codeCreateWAllArgsConstructor.getValue());
        assertNull(codeCreateWNoArgsConstructor.getValue());
    }

    @Test
    public void testCodeSettersAndGetters() {
        Code codeCreateWAllArgsConstructor = new Code("value");

        assertEquals("value", codeCreateWAllArgsConstructor.getValue());
        codeCreateWAllArgsConstructor.setValue("SecondValue");
        assertEquals("SecondValue", codeCreateWAllArgsConstructor.getValue());
    }

    @Test
    public void testCodeEqualsMethod(){
        Code codeCreateWAllArgsConstructor1 = new Code("value");
        Code codeCreateWAllArgsConstructor2 = new Code("value1");
        Code codeCreateWAllArgsConstructor3 = new Code("value");

        assertEquals(codeCreateWAllArgsConstructor1, codeCreateWAllArgsConstructor3);
        assertNotEquals(codeCreateWAllArgsConstructor1, codeCreateWAllArgsConstructor2);
        assertNotEquals(codeCreateWAllArgsConstructor2, codeCreateWAllArgsConstructor3);
    }
}
