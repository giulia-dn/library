package com.example.demo.dao;

import com.example.demo.model.Member;

import java.util.List;

public interface MemberDao {
    public void addMember(Member member);
    public void removeMember(Member member);
    public void updateMember(Member member);
    public Member getMember(String id);
    public List<Member> getAllMembers();


}
