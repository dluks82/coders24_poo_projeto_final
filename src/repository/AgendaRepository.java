package repository;

import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {
    private static List<Contact> contacts = new ArrayList<>();

    private int proximoId = 1;

    public int size() {
        return contacts.size();
    }

    public boolean create(Contact contact) {

        if (telefoneOuEmailDuplicado(-1, contact)) return false;

        contact.setId(String.valueOf(proximoId));

        contacts.add(contact);

        proximoId++;

        return true;
    }

    public boolean update(Contact contact) {
        int index = verificarIdExiste(contact.getId());
        if (index < 0) return false;

        if (telefoneOuEmailDuplicado(Integer.parseInt(contact.getId()), contact)) return false;

        Contact old = contacts.get(index);

        if (contact.getNome().trim().isEmpty())
            contact.setNome(old.getNome());

        if (contact.getEmail().trim().isEmpty())
            contact.setEmail(old.getEmail());

        if (contact.getTelefone().trim().isEmpty())
            contact.setTelefone(old.getTelefone());

        contacts.set(index, contact);

        return true;
    }

    public Contact read(String id) {
        int index = verificarIdExiste(id);
        if (index < 0) return null;

        return contacts.get(index);
    }

    public boolean remove(String id) {
        int index = verificarIdExiste(id);
        if (index < 0) return false;

        contacts.remove(index);

        return true;
    }

    public List<Contact> list() {
        return contacts;
    }

    private static int verificarIdExiste(String id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private static int verificarTelefoneExiste(String telefone) {
        for (int i = 0; i < contacts.size(); i++) {
            String telefoneItem = contacts.get(i).getTelefone();
            if (telefoneItem.trim()
                    .replace(" ", "")
                    .equals(telefone.trim()
                            .replace(" ", ""))) {
                return i;
            }
        }
        return -1;
    }

    private static int verificarEmailExiste(String email) {
        for (int i = 0; i < contacts.size(); i++) {
            String emailItem = contacts.get(i).getEmail();
            if (emailItem.trim().equals(email.trim())) {
                return i;
            }
        }
        return -1;
    }

    private static boolean telefoneOuEmailDuplicado(int indiceContato, Contact contatoEditado) {
        int indiceTelefoneDuplicado = verificarTelefoneExiste(contatoEditado.getTelefone());
        if (indiceTelefoneDuplicado >= 0 && indiceTelefoneDuplicado != indiceContato) {
            System.out.println("Telefone já cadastrado!");
            return true;
        }

        int indiceEmailDuplicado = verificarEmailExiste(contatoEditado.getEmail());
        if (indiceEmailDuplicado >= 0 && indiceEmailDuplicado != indiceContato) {
            System.out.println("Email já cadastrado!");
            return true;
        }

        return false;
    }

}
