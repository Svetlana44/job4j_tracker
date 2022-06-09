import org.junit.Test;
import ru.job4j.collection.Citizen;
import ru.job4j.collection.PassportOffice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PassportOfficeTest {

    @Test
    public void whenAdd() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertEquals(office.get(citizen.getPassport()), citizen);
    }

    @Test
    public void whenAddDouble() {
        Citizen citizen1 = new Citizen("2f44a", "Petr Arsentev");
        Citizen citizen2 = new Citizen("2f44a", "Petr Arsentev");
        Citizen citizen3 = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice passportOffice = new PassportOffice();
        passportOffice.add(citizen1);
        assertFalse(passportOffice.add(citizen2));
        assertFalse(passportOffice.add(citizen3));
    }
}