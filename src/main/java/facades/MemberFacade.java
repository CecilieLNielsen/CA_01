package facades;

import dtos.MemberDTO;
import entities.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    static MemberFacade getFacadeExample(EntityManagerFactory emf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Private Constructor to ensure Singleton
    private MemberFacade() {}
    
    /**
     * 
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
    
    public List<MemberDTO> getAllMembers(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Member> query =  em.createQuery("SELECT m FROM Member m",Member.class);
        List<Member> members = query.getResultList();
        List<MemberDTO> memberDTOs = new ArrayList();
        for (Member member : members){
            memberDTOs.add(new MemberDTO(member));
        }
        return memberDTOs;
    }
    
    //TODO Remove/Change this before use
 /*   public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
*/
    
    
    public static void main(String [] args){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.createQuery("DELETE from Member").executeUpdate();
            em.persist(new Member("Abed", "cph-ab123", "Breaking Bad"));
            em.persist(new Member("Ali", "cph-cd234", "Fresh Prince"));
            em.persist(new Member("Cecilie", "cph-ef345", "Friends"));
            em.persist(new Member("Rasmus", "cph-gh456", "The Simpsons"));
        } finally{
            em.close();
        }
    }
}
