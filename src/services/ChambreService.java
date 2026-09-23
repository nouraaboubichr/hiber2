/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author hp
 */

import dao.IDao;
import entities.Chambre;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ChambreService implements IDao<Chambre> {

    @Override
    public boolean create(Chambre o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(Chambre o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(Chambre o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Chambre findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return (Chambre) session.get(Chambre.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Chambre> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Chambre").list();
        } finally {
            if (session != null) session.close();
        }
    }

    // Afficher les chambres par hôtel
    @SuppressWarnings("unchecked")
    public List<Chambre> findByHotel(int hotelId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Chambre c where c.hotel.id = :hid")
                    .setParameter("hid", hotelId)
                    .list();
        } finally {
            if (session != null) session.close();
        }
    }

    // Chercher les chambres par état et prix (prix <= prixMax)
    @SuppressWarnings("unchecked")
    public List<Chambre> findByEtatAndPrix(String etat, double prixMax) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery(
                    "from Chambre c where c.etat = :etat and c.prix <= :prix")
                    .setParameter("etat", etat)
                    .setParameter("prix", prixMax)
                    .list();
        } finally {
            if (session != null) session.close();
        }
    }
}