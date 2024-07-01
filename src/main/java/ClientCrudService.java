
import model.Client;
import model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class ClientCrudService {
    SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    public Client getClientById(int id) {
        Client client = null;
        try (Session session = sessionFactory.openSession()) {
            client = session.find(Client.class, id);
        }
        if (client == null) {
            throw new RuntimeException("Wrong id");
        }
        return client;
    }

    public void createClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        }

    }

    public boolean updateClient(int id, String newName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Client clientById = getClientById(id);
            clientById.setName(newName);
            session.merge(clientById);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteTicket(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            session.remove(ticket);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;

        }

    }
}
