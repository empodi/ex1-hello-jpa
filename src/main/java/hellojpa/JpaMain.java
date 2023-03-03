package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

//        /* DB 저장 */
//        tx.begin();
//        Member member = new Member();
//        member.setId(1L);
//        member.setName("nameA");
//        em.persist(member);
//        tx.commit();
//        em.close();

//        /* DB 조회 */
//        tx.begin();
//        try {
//            Member member = em.find(Member.class, 1L);
//            System.out.println("findMember = " + member.getId());
//            System.out.println("findMember = " + member.getName());
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//            // 꼭 em.close를 해주어야 한다.
//        }

        tx.begin();
        try {
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(10)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

//        /* DB UPDATE */
//        tx.begin();
//        try {
//            Member member = em.find(Member.class, 1L);
//            member.setName("newName");
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//            // 꼭 em.close를 해주어야 한다.
//        }

        emf.close();
    }
}
