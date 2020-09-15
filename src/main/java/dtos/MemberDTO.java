package dtos;

import entities.Member;
import java.io.Serializable;

/**
 *
 * @author hassanainali
 */
public class MemberDTO implements Serializable {

    private long id;
    private String name;
    private String studentId;
    private String favTvShow;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.studentId = member.getStudentId();
        this.favTvShow = member.getFavTvShow();
    }

    public MemberDTO(String name, String studentId, String favTvShow) {
        this.name = name;
        this.studentId = studentId;
        this.favTvShow = favTvShow;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
