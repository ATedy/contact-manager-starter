package com.programming.techie;

import org.junit.jupiter.api.*;

class ContactManagerTest {
   ContactManager contactManager;

   @BeforeAll
   public static void setupAll() {
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

}
