package br.com.harbitech.school.course;

import java.time.OffsetDateTime;

public class Course {

    private Long id;
    private String codeUrl;
    private String name;
    private OffsetDateTime tempoFinalizacao;
    private TypeVisibilityCourse visibilityCourse;
    private String publicoAlvo;
    private String instrutor;
    private String ementa;
    private String habilidadesDesenvolvidas;
    private Course course;

    public Course(Long id, String codeUrl, String name, OffsetDateTime tempoFinalizacao,
                  TypeVisibilityCourse visibilityCourse, String publicoAlvo, String instrutor,
                  String ementa, String habilidadesDesenvolvidas, Course course) {
        this.id = id;
        this.codeUrl = codeUrl;
        this.name = name;
        this.tempoFinalizacao = tempoFinalizacao;
        this.visibilityCourse = visibilityCourse;
        this.publicoAlvo = publicoAlvo;
        this.instrutor = instrutor;
        this.ementa = ementa;
        this.habilidadesDesenvolvidas = habilidadesDesenvolvidas;
        this.course = course;
    }

    Long getId() {
      return id;
   }

    String getCodeUrl() {
      return codeUrl;
   }

    String getName() {
      return name;
   }

    OffsetDateTime getTempoFinalizacao() {
      return tempoFinalizacao;
   }

    TypeVisibilityCourse getVisibilityCourse() {
      return visibilityCourse;
   }

    String getPublicoAlvo() {
      return publicoAlvo;
   }

    String getInstrutor() {
      return instrutor;
   }

    String getEmenta() {
      return ementa;
   }

    String getHabilidadesDesenvolvidas() {
      return habilidadesDesenvolvidas;
   }

    public Course getCourse() {
        return course;
    }

    public static void validateUrl(String codeUrl) {
        boolean validacao = codeUrl.matches("[a-z]*");
        if (!validacao) {
            throw new RuntimeException("Não validado");
        }
        System.out.println("Validado");
    }
}
