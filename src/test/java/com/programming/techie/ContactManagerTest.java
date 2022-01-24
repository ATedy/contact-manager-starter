package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

// By instanciating the test here we don't need to say static in the Before and AfterAll tests.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
   ContactManager contactManager;

   @BeforeAll
   public void setupAll() {
      System.out.println("Should Print Before All Tests");
   }

   @BeforeEach
   public void setup() {
       contactManager = new ContactManager();
   }

   @Test
   public void shouldCreateContact() {
      contactManager.addContact("John", "Doe", "0123456789");
      //      using assertions we are checking the values are present or not
      Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
      Assertions.assertEquals(1,contactManager.getAllContacts().size());
      Assertions.assertTrue(contactManager.getAllContacts().stream()
              .filter(contact -> contact.getFirstName().equals("John") &&
                      contact.getLastName().equals("Doe") &&
                      contact.getPhoneNumber().equals("0123456789"))
              .findAny()
              .isPresent());

   }
   // Neagtive test Scenarios

   // When first name is null
   @Test
   @DisplayName("Should not Create Contact When First Name is Null")
   public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
      Assertions.assertThrows(RuntimeException.class, (() -> {
         contactManager.addContact("Jon", null, "0123456789");
      }));
   }

   // When last name is null
   @Test
   @DisplayName("Should not Create Contact When Last Name is Null")
   public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
      Assertions.assertThrows(RuntimeException.class, (() -> {
         contactManager.addContact(null, "Doe", "0123456789");
      }));
   }

   // When phone number is null
   @Test
   @DisplayName("Should not Create Contact When Phone Number is Null")
   public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
      Assertions.assertThrows(RuntimeException.class, (() -> {
         contactManager.addContact("Jon", "Doe", null);
      }));
   }

   @AfterEach
   public void tearDown() {
      System.out.println("Should Execute After Each Test");
   }

   @AfterAll
   public void tearDownAll() {
      System.out.println("Should be executed at the end of the Test");
   }

   // Enabling and disabling test based on OS types added
   @Test
   @DisplayName("Should not Create Contact Only on MAC OS")
   @EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on MAC")
   public void shouldCreateContactOnlyOnMAC() {
      contactManager.addContact("John", "Doe", "0123456789");
      Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
      Assertions.assertEquals(1,contactManager.getAllContacts().size());
      Assertions.assertTrue(contactManager.getAllContacts().stream()
              .filter(contact -> contact.getFirstName().equals("John") &&
                      contact.getLastName().equals("Doe") &&
                      contact.getPhoneNumber().equals("0123456789"))
              .findAny()
              .isPresent());

   }


   @Test
   @DisplayName("Should not Create Contact Only on MAC OS")
   @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Enabled only on Windows OS")
   public void shouldCreateContactOnlyOnWindows() {
      contactManager.addContact("John", "Doe", "0123456789");
      Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
      Assertions.assertEquals(1,contactManager.getAllContacts().size());
      Assertions.assertTrue(contactManager.getAllContacts().stream()
              .filter(contact -> contact.getFirstName().equals("John") &&
                      contact.getLastName().equals("Doe") &&
                      contact.getPhoneNumber().equals("0123456789"))
              .findAny()
              .isPresent());

   }

   @Test
   @DisplayName("Test Contact Creation on Developer Machine")
   public void shouldTestContactCreationOnDev() {
      Assumptions.assumeTrue("Test".equals(System.getProperty("ENV")));
      contactManager.addContact("John", "Doe", "0123456789");
      Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
      Assertions.assertEquals(1,contactManager.getAllContacts().size());
      Assertions.assertTrue(contactManager.getAllContacts().stream()
              .filter(contact -> contact.getFirstName().equals("John") &&
                      contact.getLastName().equals("Doe") &&
                      contact.getPhoneNumber().equals("0123456789"))
              .findAny()
              .isPresent());

   }

// Repeated Tests
   @DisplayName("Repeat Contact Creation 5 Times")
   @RepeatedTest(value = 5,
   name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
   public void shouldTestContactCreationRepeatedly() {
      contactManager.addContact("John", "Doe", "0123456789");
      Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
      Assertions.assertEquals(1, contactManager.getAllContacts().size());
   }


   // Parameterized  Tests
   @DisplayName("Repeat Contact Creation 5 Times")
   @ParameterizedTest
   @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
   public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
      contactManager.addContact("John", "Doe", phoneNumber);
      Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
      Assertions.assertEquals(1, contactManager.getAllContacts().size());
   }

}
