package nl.pingwins.competition.web;

import static ratpack.thymeleaf.Template.thymeleafTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.thymeleaf.ThymeleafModule;

@Configuration
public class RequestHandlers {

  @Bean
  public Action<Chain> home() {
    return
      chain -> chain.get("a", ctx -> {
        ctx.render("A Hallo!");
      }).get("b", ctx -> {
        ctx.render(thymeleafTemplate("fileUpload",
                m -> m.put("name", "B Hello!")));
      }).get(ctx -> {
        ctx.render("Default Hello!");
      });
  }

  @Bean
  public ThymeleafModule thymeleafModule() {
    return new ThymeleafModule();
  }

}
