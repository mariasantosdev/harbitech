package br.com.harbitech.school.util.builder;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;

public class CategoryBuilder {
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private CategoryStatus status;
    private int orderVisualization;
    private String iconPath;
    private String htmlHexColorCode;

    public CategoryBuilder(String name, String codeUrl) {
        this.name = name;
        this.codeUrl = codeUrl;
    }

    public CategoryBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public CategoryBuilder withStudyGuide(String studyGuide){
        this.studyGuide = studyGuide;
        return this;
    }

    public CategoryBuilder withStatus(CategoryStatus status){
        this.status = status;
        return this;
    }

    public CategoryBuilder withOrderVisualization(Integer orderVisualization){
        this.orderVisualization = orderVisualization;
        return this;
    }

    public CategoryBuilder withIconPath(String iconPath){
        this.iconPath = iconPath;
        return this;
    }

    public CategoryBuilder withHtmlHexColorCode(String htmlHexColorCode){
        this.htmlHexColorCode = htmlHexColorCode;
        return this;
    }

    public Category create(){
        return new Category(name, codeUrl, status, orderVisualization, iconPath, htmlHexColorCode, description, studyGuide);
    }

    public static Category dataScienceCategory(CategoryStatus status) {
        Category dataScience = new CategoryBuilder("Data Science", "data-science")
                .withStatus(status)
                .withDescription("Com o avanço da tecnologia, está cada vez maior a quantidade de dados disponíveis" +
                        " para análises avançadas. Desta forma, a área de Ciência de Dados emergiu auxiliando empresas" +
                        " e profissionais em suas tomadas de decisões: por isso, cresce a demanda por especialistas na " +
                        "área e o desenvolvimento de uma cultura de dados, empoderando profissionais de todas as áreas " +
                        "a lidar com dados e gerar insights que vão realmente impactar suas áreas de atuação.")
                .withOrderVisualization(1)
                .withHtmlHexColorCode("#008000")
                .withIconPath("https://www.alura.com.br/cursos-online-data-science/data-science")
                .create();
        return dataScience;
    }

    public static Category mobileCategory(CategoryStatus status) {
        Category mobile = new CategoryBuilder("Mobile", "mobile")
                .withDescription("Crie aplicativos móveis para as principais plataformas, smartphones e tablets. " +
                        "Aprenda frameworks multiplataforma como Flutter e React Native e saiba como criar apps" +
                        " nativas para Android e iOS. Desenvolva também jogos mobile com Unity. Saiba como ")
                .withStudyGuide("Android, Testes automatizados, arquitetura android e flutter")
                .withStatus(status)
                .withOrderVisualization(3)
                .withIconPath("https://www.google.com/search?q=forma%C3%A7%C3%A3o+mobile+alura+icon&client=ubuntu&hs=" +
                        "PUH&channel=fs&sxsrf=ALeKk01O4vjVbL33VupNCbN27rcLhDfgmQ:1625529442736&source=lnms&tbm=i" +
                        "sch&sa=X&ved=2ahUKEwjW-IWIkc3xAhWLK7kGHVP_BVUQ_AUoAXoECAEQAw&biw=1445&bih=733#imgrc=xIoKd" +
                        "XUJ9UtPvM")
                .withHtmlHexColorCode("#FFFF00")
                .create();
        return mobile;
    }

    public static Category frontEndCategory(CategoryStatus status){
        Category frontEnd  = new CategoryBuilder("Front end", "front-end")
                .withStatus(status)
                .withDescription("Desenvolva sites e webapps com HTML, CSS e JavaScript. Aprenda as boas práticas e as " +
                        "últimas versões do JavaScript. Estude ferramentas e frameworks do mercado como React, Angular," +
                        " Webpack, jQuery e mais. Saiba como começar com Front-end.")
                .withOrderVisualization(4)
                .withHtmlHexColorCode("#fh2893")
                .withIconPath("https://www.alura.com.br/cursos-online-front-end")
                .create();

        return frontEnd;
    }

}