package ee.ivkhkdev.services;

import ee.ivkhkdev.repositories.Repository;

import java.util.List;

public interface Service<T> {
    boolean add();
    boolean print();
    Repository<T> getRepository();}
