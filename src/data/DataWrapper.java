package data;

import model.Contact;

import java.util.List;

public record DataWrapper(List<Contact> contacts, int nextId) {
}