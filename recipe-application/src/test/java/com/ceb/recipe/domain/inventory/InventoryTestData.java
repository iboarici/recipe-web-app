package com.ceb.recipe.domain.inventory;

import com.ceb.recipe.domain.inventory.core.model.CookingInstruction;
import com.ceb.recipe.domain.inventory.core.model.Ingredient;
import com.ceb.recipe.domain.inventory.core.model.Recipe;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryTestData {

    public static List<Recipe> generate() {
        List<Recipe> recipes = new ArrayList<>();

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(Ingredient.builder().name("ince köftelik bulgur").quantity(2).unit("su bardağı").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("irmik").quantity(1).unit("su bardağı").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("yumurta").quantity(1).unit("adet").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("un").quantity(3).unit("yemek kaşığı").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("domates salçası").quantity(1).unit("yemek kaşığı").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("biber salçası").quantity(1).unit("yemek kaşığı").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("zeytinyağı").quantity(1).unit("yemek kaşığı").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("pulbiber").quantity(1).unit("çay kaşığı").size("").additionalInfo("silme").build());
        ingredients.add(Ingredient.builder().name("sıcak su").quantity(3).unit("su bardağı").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("kıyma").quantity(550).unit("gram").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("soğan").quantity(4).unit("adet").size("orta boy").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("tuz").quantity(4).unit("çay kaşığı").size("").additionalInfo("").build());
        ingredients.add(Ingredient.builder().name("nane").quantity(1).unit("çay kaşığı").size("").additionalInfo("arzuya göre").build());
        ingredients.add(Ingredient.builder().name("pul biber").quantity(2).unit("çay kaşığı").size("").additionalInfo("arzuya göre").build());
        ingredients.add(Ingredient.builder().name("kekik").quantity(1).unit("çay kaşığı").size("").additionalInfo("arzuya göre").build());
        ingredients.add(Ingredient.builder().name("karabiber").quantity(1).unit("çay kaşığı").size("").additionalInfo("arzuya göre").build());
        ingredients.add(Ingredient.builder().name("kimyon").quantity(1).unit("çay kaşığı").size("").additionalInfo("arzuya göre").build());
        ingredients.add(Ingredient.builder().name("maydonoz").quantity(1).unit("çay kaşığı").size("").additionalInfo("arzuya göre").build());
        ingredients.add(Ingredient.builder().name("ceviz içi").quantity(1).unit("dilediğiniz kadar").size("").additionalInfo("arzuya göre").build());
        ingredients.add(Ingredient.builder().name("tereyağı veya margarin").quantity(200).unit("gram").size("").additionalInfo("arzuya göre").build());

        List<CookingInstruction> cookingInstructions = new ArrayList<>();
        cookingInstructions.add(CookingInstruction.builder().description("Bulguru ve irmiği ağzı kapaklı bir kapta ıslatın ve ağzını kapatıp yaklaşık 30 dakika dinlenmeye bırakın.").build());
        cookingInstructions.add(CookingInstruction.builder().description("200 gram yağı tencereye alın ve kıymayı rengi dönene kadar sürekli karıştırarak pişirin").build());
        cookingInstructions.add(CookingInstruction.builder().description("Kıymanın her yeri renk aldıktan sonra tane tane olması için yarım çay bardağı su ekleyin ve suyunu çekene kadar kavurun.").build());
        cookingInstructions.add(CookingInstruction.builder().description("Suyunu çektikten sonra küp küp doğranmış soğanı ilave edip kavurmaya devam edin.").build());
        cookingInstructions.add(CookingInstruction.builder().description("Soğanlar tamamen kavrulduktan sonra 2 çay kaşığı tuzu ve baharatları ilave edin").build());
        cookingInstructions.add(CookingInstruction.builder().description("Cevizleri de ilave edip ocağın altını kapatın. Maydanozları ekleyip son kez karıştırıp soğuması için başka bir kaba alın.").build());
        cookingInstructions.add(CookingInstruction.builder().description("Şişmeye bıraktığımız bulhurlu karışımı bir tepsiye (varsa çiğ köfte tepsisi) veya bir kaba çıkarın ve üzerine yağ, salça, un, yumurta, pul biber ve 2 çay kaşığı tuzu ekleyip güzelce yoğurun").build());
        cookingInstructions.add(CookingInstruction.builder().description("Yaklaşık 15 dakika yoğurduktan sonra bütün hamuru bir araya getirin ve üzerini nemli bir bezle örtün. Yaklaşık 30 dakika dinlendirin.").build());
        cookingInstructions.add(CookingInstruction.builder().description("Hamurunuzdan cevizden büyük yumurtadan küçük parçalar alıp yuvarlayın. Baş veya işaret parmağınızla açıp içine harç doldurup kapatın.").build());
        cookingInstructions.add(CookingInstruction.builder().description("Eğer elinizde açamıyorsanız hamuru yağlı kağıdın arasına koyup büyükçe açarak kalıplar halinde çıkardığınız parçalara poğaça gibi iç harç koyup kapatabilirsiniz.").build());
        cookingInstructions.add(CookingInstruction.builder().description("Kızgın yağda kızartın. Dilerseniz suda da haşlayabilirsiniz.").build());



        recipes.add(Recipe.builder()
                .id(1L)
                .title("İçli Köfte")
                
                .creationDate(LocalDateTime.ofInstant(Instant.now().minus(new Random().nextInt(1000), ChronoUnit.DAYS), ZoneOffset.UTC))
                .vegetarian(false)
                .suitableForNumberOfPeople(4)
                .prepareTimeInMinutes(40)
                .cookingTimeInMinutes(120)
                .calories(1500)
                .imageUrl(null)
                .videoUrl(null)
                .createdUser("admin")
                .category("Dinner")
                .ingredients(ingredients)
                .cookingInstructions(cookingInstructions)
                .build());


        recipes.add(Recipe.builder()
                .id(2L)
                .title("Izgara Tavuk")
                .creationDate(LocalDateTime.ofInstant(Instant.now().minus(new Random().nextInt(1000), ChronoUnit.DAYS), ZoneOffset.UTC))
                .vegetarian(false)
                .suitableForNumberOfPeople(6)
                .prepareTimeInMinutes(40)
                .cookingTimeInMinutes(30)
                .calories(700)
                .imageUrl(null)
                .videoUrl(null)
                .category("Dinner")
                .createdUser("admin").build());

        recipes.add(Recipe.builder()
                .id(3L)
                .title("Kuru Fasulye")
                .creationDate(LocalDateTime.ofInstant(Instant.now().minus(new Random().nextInt(1000), ChronoUnit.DAYS), ZoneOffset.UTC))
                .vegetarian(false)
                .suitableForNumberOfPeople(4)
                .prepareTimeInMinutes(40)
                .cookingTimeInMinutes(60)
                .calories(900)
                .imageUrl(null)
                .videoUrl(null)
                .category("Dinner")
                .createdUser("admin").build());

        recipes.add(Recipe.builder()
                .id(4L)
                .title("Taze Fasulye")
                .creationDate(LocalDateTime.ofInstant(Instant.now().minus(new Random().nextInt(1000), ChronoUnit.DAYS), ZoneOffset.UTC))
                .vegetarian(false)
                .suitableForNumberOfPeople(2)
                .prepareTimeInMinutes(40)
                .cookingTimeInMinutes(60)
                .calories(600)
                .imageUrl(null)
                .videoUrl(null)
                .category("Dinner")
                .createdUser("admin").build());

        recipes.add(Recipe.builder()
                .id(5L)
                .title("Sucuklu Yumurta")
                .creationDate(LocalDateTime.ofInstant(Instant.now().minus(new Random().nextInt(1000), ChronoUnit.DAYS), ZoneOffset.UTC))
                .vegetarian(false)
                .suitableForNumberOfPeople(2)
                .prepareTimeInMinutes(5)
                .cookingTimeInMinutes(5)
                .calories(500)
                .imageUrl(null)
                .videoUrl(null)
                .category("Dinner")
                .createdUser("admin").build());

        recipes.add(Recipe.builder()
                .id(6L)
                .title("Sigara Boregi")
                .creationDate(LocalDateTime.ofInstant(Instant.now().minus(new Random().nextInt(1000), ChronoUnit.DAYS), ZoneOffset.UTC))
                .vegetarian(false)
                .suitableForNumberOfPeople(2)
                .prepareTimeInMinutes(50)
                .cookingTimeInMinutes(10)
                .calories(1200)
                .imageUrl(null)
                .videoUrl(null)
                .category("Dinner")
                .createdUser("admin").build());

        recipes.add(Recipe.builder()
                .id(7L)
                .title("Patates Kızartması")
                .creationDate(LocalDateTime.ofInstant(Instant.now().minus(new Random().nextInt(1000), ChronoUnit.DAYS), ZoneOffset.UTC))
                .vegetarian(false)
                .suitableForNumberOfPeople(2)
                .prepareTimeInMinutes(30)
                .cookingTimeInMinutes(10)
                .calories(1600)
                .imageUrl(null)
                .videoUrl(null)
                .category("Snack")
                .createdUser("admin").build());

        return recipes;
    }
}
