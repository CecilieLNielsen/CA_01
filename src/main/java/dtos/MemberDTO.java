package dtos;

import entities.Member;

/**
 *
 * @author hassanainali
 */
public class MemberDTO {
    private int id;
    private String name;
    private String studentId;
    private String favTvShow;

    public MemberDTO(Member member) {
        this.name = member.getName();
        this.studentId = member.getStudentId();
        this.favTvShow = member.getFavTvShow();
    }

    public MemberDTO(int id, String name, String studentId, String favTvShow) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.favTvShow = favTvShow;
    }

    public MemberDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFavTvShow() {
        return favTvShow;
    }

    public void setFavTvShow(String favTvShow) {
        this.favTvShow = favTvShow;
    }
}



