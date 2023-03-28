package monprojet.me.tp1j2ee;

import monprojet.me.tp1j2ee.entities.Patient;
import monprojet.me.tp1j2ee.repositories.PatientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;



@SpringBootApplication
public class Tp1J2EeApplication implements CommandLineRunner {
    @Autowired
    private PatientRepositories patientRepositories;
    public static void main(String[] args) {
        SpringApplication.run(Tp1J2EeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
     for (int i= 0; i<100; i++) {
         patientRepositories.save(
                 new Patient(null, "meriem", new Date(), Math.random()>0.5?true:false,(int)(Math.random()*100)));
     }
        patientRepositories.save(
                new Patient(null,"imran",new Date(),true,20));
        patientRepositories.save(
                new Patient(null,"anass",new Date(),false,100));
        Page<Patient> patients = patientRepositories.findAll(PageRequest.of( 1, 5));
        System.out.println("Total pages:"+patients.getTotalPages());
        System.out.println("Total elements"+patients.getTotalElements());
        System.out.println("Num page:"+patients.getNumber());
        List<Patient> content = patients.getContent();
        Page<Patient> byMalade = patientRepositories.findByMalade(true, PageRequest.of(0, 4));
        byMalade.forEach(p->{
            System.out.println("===========================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());



        });
        System.out.println("******************************");
        Patient patient=patientRepositories.findById(1L).orElse(null);
        if(patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        patient.setScore(870);
        patientRepositories.save(patient);
        patientRepositories.deleteById(1L);
    }
}
