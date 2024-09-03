package repository;

import data.DataPersistence;
import data.DataWrapper;
import model.Contact;

import java.util.List;

public class AgendaRepository {
    private final List<Contact> contacts;

    private int nextId;

    public AgendaRepository() {
        DataWrapper dataWrapper = DataPersistence.load();
        this.contacts = dataWrapper.contacts();
        this.nextId = dataWrapper.nextId();
    }

    public int size() {
        return contacts.size();
    }

    public boolean create(Contact contact) {

        if (duplicatePhoneOrEmail(-1, contact)) return false;

        contact.setId(String.valueOf(nextId));

        contacts.add(contact);

        nextId++;

        saveData();

        return true;
    }

    private void saveData() {
        DataPersistence.save(new DataWrapper(contacts, nextId));
    }

    public boolean update(Contact contact) {
        int index = checkIfIdExists(contact.getId());
        if (index < 0) return false;

        if (duplicatePhoneOrEmail(Integer.parseInt(contact.getId()), contact)) return false;

        Contact old = contacts.get(index);

        if (contact.getName().trim().isEmpty())
            contact.setName(old.getName());

        if (contact.getEmail().trim().isEmpty())
            contact.setEmail(old.getEmail());

        if (contact.getPhone().trim().isEmpty())
            contact.setPhone(old.getPhone());

        contacts.set(index, contact);

        saveData();

        return true;
    }

    public Contact read(String id) {
        int index = checkIfIdExists(id);
        if (index < 0) return null;

        return contacts.get(index);
    }

    public boolean remove(String id) {
        int index = checkIfIdExists(id);
        if (index < 0) return false;

        contacts.remove(index);

        saveData();

        return true;
    }

    public List<Contact> list() {
        return contacts;
    }

    private int checkIfIdExists(String id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private int checkIfPhoneExists(String phone) {
        for (int i = 0; i < contacts.size(); i++) {
            String phoneItem = contacts.get(i).getPhone();
            if (phoneItem.trim()
                    .replace(" ", "")
                    .equals(phone.trim()
                            .replace(" ", ""))) {
                return i;
            }
        }
        return -1;
    }

    private int checkIfEmailExists(String email) {
        for (int i = 0; i < contacts.size(); i++) {
            String emailItem = contacts.get(i).getEmail();
            if (emailItem.trim().equals(email.trim())) {
                return i;
            }
        }
        return -1;
    }

    private boolean duplicatePhoneOrEmail(int contactIndex, Contact editedContact) {
        int duplicatePhoneIndex = checkIfPhoneExists(editedContact.getPhone());
        if (duplicatePhoneIndex >= 0 && duplicatePhoneIndex != contactIndex) {
            System.out.println("Telefone já cadastrado!");
            return true;
        }

        int duplicateEmailIndex = checkIfEmailExists(editedContact.getEmail());
        if (duplicateEmailIndex >= 0 && duplicateEmailIndex != contactIndex) {
            System.out.println("Email já cadastrado!");
            return true;
        }
        return false;
    }
}
