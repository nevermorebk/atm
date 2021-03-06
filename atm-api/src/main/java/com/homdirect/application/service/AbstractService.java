package com.homdirect.application.service;

import com.homdirect.application.constant.ErrorCode;
import com.homdirect.application.exception.ATMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T> {

    @Autowired
    protected JpaRepository<T, Integer> jpaRepository;

    public T save(T t) {
        return jpaRepository.save(t);
    }

    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    public T findById(int id) throws ATMException {
        Optional<T> optional = jpaRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ATMException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND_MES, id);
        }

        return optional.get();
    }
}
