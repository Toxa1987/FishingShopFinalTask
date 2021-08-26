package by.toxa.fishingshop.test;

import by.toxa.fishingshop.model.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UserValidatorTest {

    @Test
    public void isValidEmailTestWithRightEmail() {
        String email = "wertyal.87@mail.ru";
        Assert.assertTrue(UserValidator.isValidEmail(email));
    }

    @Test
    public void isValidEmailTestWithWrongEmail() {
        String email = "wertyal.87@mail";
        Assert.assertFalse(UserValidator.isValidEmail(email));
    }

    @Test
    public void isValidLoginTestWithRightLogin() {
        String login = "login";
        Assert.assertTrue(UserValidator.isValidLogin(login));
    }

    @Test
    public void isValidLoginTestWithWrongLogin() {
        String email = " ";
        Assert.assertFalse(UserValidator.isValidLogin(email));
    }

    @Test
    public void isValidPasswordTestWithRightPassword() {
        String password = "password";
        Assert.assertTrue(UserValidator.isValidPassword(password));
    }

    @Test
    public void isValidPasswordTestWithWrongPassword() {
        String password = " 1_2";
        Assert.assertFalse(UserValidator.isValidPassword(password));
    }

    @Test
    public void isValidPhoneTestWithRightPhone() {
        String phone = "+375446660099";
        Assert.assertTrue(UserValidator.isValidPhone(phone));
    }
    @Test
    public void isValidPhoneTestWithWrongPhone() {
        String phone = "+355588866";
        Assert.assertFalse(UserValidator.isValidPhone(phone));
    }

    @Test
    public void isValidNameTestWithRightName() {
        String name = "Ян";
        Assert.assertTrue(UserValidator.isValidName(name));
    }

    @Test
    public void isValidNameTestWithWrongName() {
        String name = " werwe ";
        Assert.assertFalse(UserValidator.isValidName(name));
    }
    @Test
    public void isValidLastNameTestWithRightLastName() {
        String name = "Ivanov";
        Assert.assertTrue(UserValidator.isValidName(name));
    }

    @Test
    public void isValidLastNameTestWithWrongLastName() {
        String name = "Vfn1231";
        Assert.assertFalse(UserValidator.isValidName(name));
    }
}