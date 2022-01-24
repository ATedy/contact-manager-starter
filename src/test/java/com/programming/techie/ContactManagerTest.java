package com.programming.techie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactManagerTest {
   @Test
   public void shouldCreateContact() {
      ContactManager contactManager = new ContactManager();
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
      ContactManager contactManager = new ContactManager();
      Assertions.assertThrows(RuntimeException.class, (() -> {
         contactManager.addContact("Jon", null, "0123456789");
      }));
   }

   // When last name is null
   @Test
   @DisplayName("Should not Create Contact When Last Name is Null")
   public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
      ContactManager contactManager = new ContactManager();
      Assertions.assertThrows(RuntimeException.class, (() -> {
         contactManager.addContact(null, "Doe", "0123456789");
      }));
   }

   // When phone number is null
   @Test
   @DisplayName("Should not Create Contact When Phone Number is Null")
   public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
      ContactManager contactManager = new ContactManager();
      Assertions.assertThrows(RuntimeException.class, (() -> {
         contactManager.addContact("Jon", "Doe", null);
      }));
   }

}
