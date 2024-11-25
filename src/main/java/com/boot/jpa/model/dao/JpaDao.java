package com.boot.jpa.model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.jpa.model.entity.JpaEntity;


@Repository
public interface JpaDao extends JpaRepository<JpaEntity, Integer> {
	
	/*
	 * 
	 * findAll() : 모든 데이터 조회
	 * save() : 데이터 저장
	 * delete(): 데이터 삭제
	 */
	
	public List<JpaEntity> findAll();
	
	public List<JpaEntity> findAll(Sort sort);
	
	public JpaEntity save(JpaEntity dto);
	
	public JpaEntity findByMyno(int myno);

	@Transactional
	public void deleteByMyno(int myno);
	
}
