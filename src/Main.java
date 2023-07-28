import Engine.Game;

public class Main {
    public static void main(String[] args) {
        //Runnable game = new Game(1000, 1000);
        //Thread t = new Thread(game);
        //t.start();

        String s = "/summon minecraft:villager ~ ~1 ~ {VillagerData:{type:plains,profession:nitwit,level:5},Offers:{Recipes:[{maxUses:9999999,rewardExp:0b,buy:{id:diamond,Count:2},sell:{id:potion,Count:1,tag:{CustomPotionColor:16777215,display:{Name:'[{\"text\":\"Potion\"}]'},CustomPotionEffects:[{Id:6,Duration:200,Amplifier:10},{Id:2,Duration:100,Amplifier:1}]}}},{maxUses:9999999,rewardExp:0b,buy:{id:diamond,Count:1},sell:{id:potion,Count:1,tag:{CustomPotionColor:16777215,display:{Name:'[{\"text\":\"Potion\"}]'},CustomPotionEffects:[{Id:15,Duration:480,Amplifier:1},{Id:14,Duration:500,Amplifier:1}]}}},{maxUses:9999999,rewardExp:0b,buy:{id:diamond,Count:2},sell:{id:potion,Count:1,tag:{CustomPotionColor:16777215,display:{Name:'[{\"text\":\"Potion\"}]'},CustomPotionEffects:[{Id:14,Duration:200,Amplifier:1}]}}},{maxUses:9999999,rewardExp:0b,buy:{id:diamond,Count:4},sell:{id:splash_potion,Count:1,tag:{CustomPotionColor:16777215,display:{Name:'[{\"text\":\"Potion\"}]'},CustomPotionEffects:[{Id:10,Duration:1000,Amplifier:10}]}}},{maxUses:9999999,rewardExp:0b,buy:{id:diamond,Count:3},sell:{id:potion,Count:1,tag:{CustomPotionColor:16777215,display:{Name:'[{\"text\":\"Hype ixel\\'s Special\",\"italic\":false}]'},CustomPotionEffects:[{Id:14,Duration:100,Amplifier:1},{Id:8,Duration:200,Amplifier:1},{Id:1,Duration:200,Amplifier:1}]}}},{maxUses:9999999,rewardExp:0b,buy:{id:diamond,Count:1},sell:{id:splash_potion,Count:1,tag:{CustomPotionColor:16777215,display:{Name:'[{\"text\":\"Potion\"}]'},CustomPotionEffects:[{Id:25,Duration:200,Amplifier:1},{Id:28,Duration:400,Amplifier:1}]}}},{maxUses:99999999999999,rewardExp:0b,buy:{id:diamond,Count:2},sell:{id:potion,Count:1,tag:{CustomPotionColor:16777215,display:{Name:'[{\"text\":\"Potion\"}]'},CustomPotionEffects:[{Id:30,Duration:1000000000000000000,Amplifier:2}]}}},{buy:{id:diamond,Count:1},sell:{id:lingering_potion,Count:1,tag:{CustomPotionColor:16777215,display:{Name:'[{\"text\":\"Potion\"}]'},CustomPotionEffects:[{Id:24,Duration:200,Amplifier:1}]}}}]},CustomName:'[{\"text\":\"Not Man\"}]',CustomNameVisible:1b,Invulnerable:1b,NoAI:1b,PersistenceRequired:1b,Silent:1b}";

        String n = s.replace("16777215,", "16777215,Enchantments:[{id:vanishing_curse,lvl:1}],");
        System.out.println(n);
    }

}