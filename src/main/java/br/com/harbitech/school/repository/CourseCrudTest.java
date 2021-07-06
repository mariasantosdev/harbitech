package br.com.harbitech.school.repository;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.repository.dao.CategoryDao;
import br.com.harbitech.school.repository.dao.CourseDao;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.util.JPAUtil;

import javax.persistence.EntityManager;

public class CourseCrudTest {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        CourseDao courseDao = new CourseDao(em);
        registerCourse();
    }

    private static void registerCourse() {

        EntityManager em = JPAUtil.getEntityManager();
        CourseDao courseDao = new CourseDao(em);
        CategoryDao categoryDao = new CategoryDao(em);

        Category category = new Category("DevOps","devops");

        Subcategory subCategory = new Subcategory(5L,"Builds e Controle de versão",
                "builds-e-controle-de-versao", 3,"O build é a construção da aplicação " +
                "a partir do código fonte, que pode incluir várias etapas, como baixar dependências, compilar, " +
                "empacotar, testar e analisar. Como estas são uma tarefas trabalhosas e repetitivas, existem " +
                "ferramentas para autotomizar as etapas, facilitando e acelerando o desenvolvimento.",
                "Administração de rede, segurança de aplicações e web services", SubCategoryStatus.ACTIVE,category);

        Course course = new Course("Maven: Gerenciamento de dependências e build de aplicações Java","maven",8,
                "Rodrigo Ferreira",subCategory);

        em.getTransaction().begin();

//        courseDao.save(course);
//        courseDao.delete("maven");
//        courseDao.upgradeAllToPublicVisibility();
//        courseDao.searchAllWithPublicVisibility();
        categoryDao.searchAllActive();
        em.getTransaction().commit();
        em.close();
    }
}
