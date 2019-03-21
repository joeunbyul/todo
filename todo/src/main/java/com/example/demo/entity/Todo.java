package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "todo")
@Data
public class Todo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * @Id 어노테이션을 PK(Prime Key: 기본키)를 의미하며 GerneratedValue 어노테이션을 통해서 이 PK값을 어떻게 입력할 것인지 정의
	 * 
	 * GenerationType.AUTO : 특정 DB에 맞게 자동 선택 
	 * GenerationType.IDENTITY : DB의 Identity 컬럼을 이용 
	 * GenerationType.SEQUENCE : DB의 시퀀스 컬럼을 이용 
	 * GenerationType.TABLE : 유일성이 보장된 데이터베이스 테이블을 이용 
	 */
    private @Column(name = "listnum") int listnum;
    private @Column(name = "contents") String contents;
    private @Column(name = "nickname") String nickname;
     
    @CreationTimestamp //데이터를 Insert해줄때 자동으로 시간을 입력
    private @Column(name = "cdate") Timestamp cdate;

}
