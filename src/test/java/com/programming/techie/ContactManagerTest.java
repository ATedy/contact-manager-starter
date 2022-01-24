package com.programming.techie;

import org.junit.jupiter.api.Assertions;
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
}
