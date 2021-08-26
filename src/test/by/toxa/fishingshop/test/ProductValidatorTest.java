package by.toxa.fishingshop.test;

import by.toxa.fishingshop.model.validator.ProductValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ProductValidatorTest {

    @Test
    public void isValidVendorWithRightVendor() {
        String vendor = "123dd-dsd";
        Assert.assertTrue(ProductValidator.isValidVendor(vendor));
    }

    @Test
    public void isValidVendorWithWrongVendor() {
        String vendor = " &?";
        Assert.assertFalse(ProductValidator.isValidVendor(vendor));
    }

    @Test
    public void isValidNameWithRightName() {
        String name = "ToxA 123 RT";
        Assert.assertTrue(ProductValidator.isValidName(name));
    }

    @Test
    public void isValidNameWithWrongName() {
        String name = " ?oxA 123 RT";
        Assert.assertFalse(ProductValidator.isValidName(name));
    }

    @Test
    public void isValidPriceWithRightPrice() {
        String price = "1.99";
        Assert.assertTrue(ProductValidator.isValidPrice(price));
    }

    @Test
    public void isValidPriceWithWrongPrice() {
        String price = "00,00";
        Assert.assertFalse(ProductValidator.isValidPrice(price));
    }

    @Test
    public void isValidNumberInStockTestWithRightNumberInStock() {
        int number = 15;
        Assert.assertTrue(ProductValidator.isValidNumberInStock(number));
    }

    @Test
    public void isValidNumberInStockTestWithWrongNumberInStock() {
        int number = -1;
        Assert.assertFalse(ProductValidator.isValidNumberInStock(number));
    }

    @Test
    public void isValidImageWithRightImage() {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        Assert.assertTrue(ProductValidator.isValidImage(inputStream));
    }

    @Test
    public void isValidImageWithWrongImage() {
        InputStream inputStream = null;
        Assert.assertFalse(ProductValidator.isValidImage(inputStream));
    }
}