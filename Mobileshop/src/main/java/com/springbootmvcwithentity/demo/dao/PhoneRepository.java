package com.springbootmvcwithentity.demo.dao;

        import com.springbootmvcwithentity.demo.entity.Phones;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
@EnableJpaRepositories(basePackages = "com.springbootmvcwithentity.demo.dao")

public interface PhoneRepository extends JpaRepository<Phones, Integer>{
        public Phones findByModelID (int modelID);
        List<Phones> findAllByPhoneNameContainingOrSeriContaining(String search1, String search2);
        List<Phones> findAllByBrandId(int id);
        List<Phones> findAllByCategoryId(int id);


    }
