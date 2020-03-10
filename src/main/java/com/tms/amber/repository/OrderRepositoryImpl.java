package com.tms.amber.repository;

import com.tms.amber.domain.Order;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tms.amber.util.HibernateUtil;
import org.apache.log4j.Logger;

import javax.persistence.NoResultException;

public class OrderRepositoryImpl implements OrderRepository {

    private Session session = null;
    private Transaction transaction = null;
    final static Logger logger = Logger.getLogger(OrderRepositoryImpl.class);

    public OrderRepositoryImpl() {
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            orders = session.createQuery("FROM Order").getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return orders;
    }

    @Override
    public Optional<Order> getById(Long id) {
        Order order = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            order = session.get(Order.class, id);
        } catch (NoResultException nre) {
            return Optional.empty();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return Optional.ofNullable(order);
    }

    @Override
    public void deleteOrder(Order order) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(order);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void save(Order order) {
        logger.debug("save order " + order.getName());
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(order);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(Order order) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(order);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
