package facades;

import dtos.MemberDTO;
import entities.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<MemberDTO> getAllMembers() {
        EntityManager em = getEntityManager();
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
        List<Member> members = query.getResultList();
        List<MemberDTO> memberDTOs = new ArrayList();
        for (Member member : members) {
            memberDTOs.add(new MemberDTO(member));
        }
        return memberDTOs;
    }
}
