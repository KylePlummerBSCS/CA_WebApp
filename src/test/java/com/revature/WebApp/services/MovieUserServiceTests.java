package com.revature.WebApp.services;

import com.revature.WebApp.entities.UserEntity;
import com.revature.WebApp.exceptions.DataSourceException;
import com.revature.WebApp.exceptions.InvalidRequestException;
import com.revature.WebApp.repositories.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MovieUserServiceTests {

    String email = "princeshrek@email.com";
    String un = "princeshrek";
    String pw = "shrek123";
    String fn = "Shrek";
    String ln = "Prince";

    MovieUserService sut;
    UserRepository mockUserRepository;
    UserEntity mockUserEntity;

    @Before
    public void setUp() {
        mockUserRepository = mock(UserRepository.class);
        mockUserEntity = mock(UserEntity.class);
        sut = new MovieUserService(mockUserRepository);
    }

    @After
    public void tearDown() {
        sut = null;
        mockUserEntity = null;
        mockUserRepository = null;
    }

    @Test
    public void testRegister() {

        //Arrange
        UserEntity testUser = new UserEntity();
        testUser.setEmail(email);
        testUser.setUsername(un);
        testUser.setPassword(pw);
        testUser.setFirstName(fn);
        testUser.setLastName(ln);
        mockUserEntity = spy(testUser);
        when(mockUserRepository.save(mockUserEntity)).thenReturn(mockUserEntity);

        //Act
        sut.register(mockUserEntity);

        //Assert
        verify(mockUserRepository, times(1)).save(any());
    }

    @Test
    public void testIsUserValidWithValid() {

        //Arrange
        boolean testBoolean;
        UserEntity testUser = new UserEntity();
        testUser.setEmail(email);
        testUser.setUsername(un);
        testUser.setPassword(pw);
        testUser.setFirstName(fn);
        testUser.setLastName(ln);
        mockUserEntity = spy(testUser);

        //Act
        testBoolean = sut.isUserValid(mockUserEntity);

        //Assert
        Assert.assertTrue(testBoolean);
    }

    @Test(expected = InvalidRequestException.class)
    public void testIsUserValidWithInvalidEmail() {
        /*
         * There is no need to continue with specific invalid items
         * like email, since the called function for those are tested
         * below, BUT for the sake of line coverage, I must!
         */

        //Arrange
        UserEntity testUser = new UserEntity();
        testUser.setEmail("");
        testUser.setUsername(un);
        testUser.setPassword(pw);
        testUser.setFirstName(fn);
        testUser.setLastName(ln);
        mockUserEntity = spy(testUser);

        //Act
        sut.isUserValid(mockUserEntity);

        //Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void testIsUserValidWithInvalidUsername() {

        //Arrange
        UserEntity testUser = new UserEntity();
        testUser.setEmail(email);
        testUser.setUsername("");
        testUser.setPassword(pw);
        testUser.setFirstName(fn);
        testUser.setLastName(ln);
        mockUserEntity = spy(testUser);

        //Act
        sut.isUserValid(mockUserEntity);

        //Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void testIsUserValidWithInvalidPassword() {

        //Arrange
        UserEntity testUser = new UserEntity();
        testUser.setEmail(email);
        testUser.setUsername(un);
        testUser.setPassword("");
        testUser.setFirstName(fn);
        testUser.setLastName(ln);
        mockUserEntity = spy(testUser);

        //Act
        sut.isUserValid(mockUserEntity);

        //Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void testIsUserValidWithInvalidFirstName() {

        //Arrange
        UserEntity testUser = new UserEntity();
        testUser.setEmail(email);
        testUser.setUsername(un);
        testUser.setPassword(pw);
        testUser.setFirstName("");
        testUser.setLastName(ln);
        mockUserEntity = spy(testUser);

        //Act
        sut.isUserValid(mockUserEntity);

        //Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void testIsUserValidWithInvalidLastName() {

        //Arrange
        UserEntity testUser = new UserEntity();
        testUser.setEmail(email);
        testUser.setUsername(un);
        testUser.setPassword(pw);
        testUser.setFirstName(fn);
        testUser.setLastName("");
        mockUserEntity = spy(testUser);

        //Act
        sut.isUserValid(mockUserEntity);

        //Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void testIsUserValidWithNull() {
        //Arrange

        //Act
        sut.isUserValid(null);

        //Assert

    }

    @Test
    public void testIsEmailAvailableWhenAvailable() {

        //Arrange
        boolean testBoolean;
        when(mockUserRepository.isEmailAvailable(email)).thenReturn(true);

        //Act
        testBoolean = sut.isEmailAvailable(email);

        //Assert
        Assert.assertTrue(testBoolean);
    }

    @Test
    public void testIsEmailAvailableWhenNotAvailable() {

        //Arrange
        boolean testBoolean;
        when(mockUserRepository.isEmailAvailable(email)).thenReturn(false);

        //Act
        testBoolean = sut.isEmailAvailable(email);

        //Assert
        Assert.assertFalse(testBoolean);
    }

    @Test(expected = DataSourceException.class)
    public void testIsEmailAvailableWhenCommunicationFailure() {

        //Arrange
        when(mockUserRepository.isEmailAvailable(email)).thenThrow(DataSourceException.class);

        //Act
        sut.isEmailAvailable(email);

        //Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void testIsEmailAvailableWhenInvalid() {

        //Arrange
        when(mockUserRepository.isEmailAvailable("")).thenThrow(DataSourceException.class);

        //Act
        sut.isEmailAvailable("");

        //Assert
    }

    @Test
    public void testIsUsernameAvailableWhenAvailable() {

        //Arrange
        boolean testBoolean;
        when(mockUserRepository.isUsernameAvailable(un)).thenReturn(true);

        //Act
        testBoolean = sut.isUsernameAvailable(un);

        //Assert
        Assert.assertTrue(testBoolean);
    }

    @Test
    public void testIsUsernameAvailableWhenNotAvailable() {

        //Arrange
        boolean testBoolean;
        when(mockUserRepository.isUsernameAvailable(un)).thenReturn(false);

        //Act
        testBoolean = sut.isUsernameAvailable(un);

        //Assert
        Assert.assertFalse(testBoolean);
    }

    @Test(expected = DataSourceException.class)
    public void testIsUsernameAvailableWhenCommunicationFailure() {

        //Arrange
        when(mockUserRepository.isUsernameAvailable(un)).thenThrow(DataSourceException.class);

        //Act
        sut.isUsernameAvailable(un);

        //Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void testIsUsernameAvailableWhenInvalid() {

        //Arrange
        when(mockUserRepository.isUsernameAvailable("")).thenThrow(DataSourceException.class);

        //Act
        sut.isUsernameAvailable("");

        //Assert
    }

    @Test
    public void testAuthenticateWithAllValid() {

        //Arrange
        Object testObject;
        when(mockUserRepository.findByUsernameAndPassword(un,pw)).thenReturn(java.util.Optional.ofNullable(mockUserEntity));

        //Act
        testObject = sut.authenticate(un, pw);

        //Assert
        verify(mockUserRepository,times(1)).findByUsernameAndPassword(anyString(),anyString());
        Assert.assertNotNull(testObject);
    }

    @Test(expected = InvalidRequestException.class)
    public void testAuthenticateWithInvalidUsername() {

        //Arrange
        when(mockUserRepository.findByUsernameAndPassword("",pw)).thenReturn(java.util.Optional.ofNullable(mockUserEntity));

        //Act
        sut.authenticate("", pw);

        //Assert
        verify(mockUserRepository,times(0)).findByUsernameAndPassword(anyString(),anyString());
    }

    @Test(expected = InvalidRequestException.class)
    public void testAuthenticateWithInvalidPassword() {

        //Arrange
        when(mockUserRepository.findByUsernameAndPassword(un,"")).thenReturn(java.util.Optional.ofNullable(mockUserEntity));

        //Act
        sut.authenticate(un, "");

        //Assert
        verify(mockUserRepository,times(0)).findByUsernameAndPassword(anyString(),anyString());
    }

    @Test
    public void testIsValidWithAllValidStrings() {

        //Arrange
        String validString = "ValidString";
        String validFieldName = "username";

        //Act

        //Assert
        Assert.assertTrue(sut.isValid(validString,validFieldName));
        Assert.assertTrue(sut.isValid(validString,"firstName"));
        Assert.assertTrue(sut.isValid(validString,"lastName"));
        Assert.assertTrue(sut.isValid(validString,"password"));
        Assert.assertTrue(sut.isValid(validString,"email"));
    }

    @Test
    public void testIsValidWithInvalidFirstParameter() {

        //Arrange
        String invalidString = "";

        //Act

        //Assert
        Assert.assertFalse(sut.isValid(invalidString,"username"));
        Assert.assertFalse(sut.isValid(invalidString,"firstName"));
        Assert.assertFalse(sut.isValid(invalidString,"lastName"));
        Assert.assertFalse(sut.isValid(invalidString,"password"));
        Assert.assertFalse(sut.isValid(invalidString,"email"));
        Assert.assertFalse(sut.isValid(null,"username"));
        Assert.assertFalse(sut.isValid(null,"firstName"));
        Assert.assertFalse(sut.isValid(null,"lastName"));
        Assert.assertFalse(sut.isValid(null,"password"));
        Assert.assertFalse(sut.isValid(null,"email"));
    }

    @Test
    public void testIsValidWithInvalidFieldName() {

        //Arrange
        String validString = "ValidString";
        String invalidFieldName = "invalid";

        //Act

        //Assert
        Assert.assertFalse(sut.isValid("ValidString", invalidFieldName));
        Assert.assertFalse(sut.isValid("ValidString", ""));
        Assert.assertFalse(sut.isValid("ValidString", null));
    }
}
