/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.demos.demo2;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mygame.engine.AudioPlayer;
import mygame.engine.Sprite;
import mygame.engine.TextureAtlas;

/**
 *
 * @author jaime.tijerina01
 */
public class Game extends Sprite {

    BgGame bg = new BgGame();
    TextureAtlas anim = new TextureAtlas("Demo2Assets/sprites.xml");
    Player player;
    List<Projectile> projectiles = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
    List<Explosion> explosions = new ArrayList<>();
    AudioPlayer explosionSound;
    AudioPlayer musicSound = new AudioPlayer("Demo2Assets/Sound/gameMusic.wav");
    private int enemySpawnCounter;
    private int enemySpawnMaxCount;
    private int score;
    public boolean isGameOver;

    Hud gameHud;
    Demo2 game;

    public Game(Demo2 game) {
        super("Demo2Assets/mainbackground.png");
        this.game = game;
        player = new Player(this, anim);
        player.toggleDebug();
        gameHud = new Hud(this);
        this.player.y = game.getHeight() / 2;
        this.player.x = 0; 
    }

    @Override
    public void draw(Graphics2D g) {

        bg.draw(g);
        player.draw(g);

        gameHud.draw(g);

        //DrawProjectile
        for (int j = projectiles.size() - 1; j >= 0; j--) {
            projectiles.get(j).draw(g);
        }

        //DrawEnemies
        for (int j = enemies.size() - 1; j >= 0; j--) {
            enemies.get(j).draw(g);
        }

        //DrawExplosions
        for (int j = explosions.size() - 1; j >= 0; j--) {
            explosions.get(j).draw(g);
        }
    }

    @Override
    public void update() {

        bg.update();
        player.update();

        //AnimateProjectile
        for (int j = projectiles.size() - 1; j >= 0; j--) {
            projectiles.get(j).update();

            for (int i = enemies.size() - 1; i >= 0; i--) {
                if (projectiles.get(j).rectOverlap(enemies.get(i))) {
                    enemies.get(i).Health -= projectiles.get(j).Damage;
                    projectiles.get(j).isActive = false;
                }
            }

            //if not active delete
            if (!projectiles.get(j).isIsActive()) {
                projectiles.get(j).destroy();
                projectiles.remove(j);
            }
        }

        UpdateEnemies();

        UpdateExplosions();

        UpdateCollision();

        CheckForEndGame();
    }

    void AddProjectile() {

        Projectile projectile = new Projectile(this);
        projectile.x = this.player.x + this.player.width - projectile.width / 2;
        projectile.y = this.player.y + (this.player.height / 2) - projectile.height / 2;
        projectiles.add(projectile);

    }

    private void UpdateEnemies() {
        if (enemySpawnCounter > enemySpawnMaxCount) {
            enemySpawnCounter = 0;
            AddEnemy();
        }

        // MoveEnemies(enemies);
        // Update the Enemies
        for (int j = enemies.size() - 1; j >= 0; j--) {
            {
                enemies.get(j).update();

                if (this.player.rectOverlap(enemies.get(j))) {
                    player.Health -= enemies.get(j).Damage;

                    gameHud.setLives(player.Health);
                    enemies.get(j).Health = 0;

                    // If the player health is less than zero we died
                    if (player.Health <= 0) {
                        player.isActive = false;
                    }
                }

                if (enemies.get(j).isActive == false) {
                    AddExplosion(enemies.get(j).x, enemies.get(j).y);

                    if (enemies.get(j).x >= 0) {
                        explosionSound = new AudioPlayer("Demo2Assets/Sound/explosion.wav", true);
                        explosionSound.play();

                        score += enemies.get(j).Value;

                        gameHud.setScore(score);
                    } else {
                        player.Health -= enemies.get(j).Damage;

                        gameHud.setLives(player.Health);
                    }

                    enemies.get(j).destroy();
                    enemies.remove(j);
                }
            }

        }

        enemySpawnCounter++;
    }

    public void init() {
        musicSound.play();
        gameHud.init();
        gameHud.setLives(player.Health);
        enemySpawnCounter = 0;
        enemySpawnMaxCount = 60;
        score = 0;
        isGameOver = false;

        for (int j = explosions.size() - 1; j >= 0; j--) {
            explosions.get(j).destroy();
            explosions.remove(j);
        }

        for (int j = enemies.size() - 1; j >= 0; j--) {
            enemies.get(j).destroy();
            enemies.remove(j);
        }

    }

    private void AddEnemy() {
        float scoreAdder = (score / 1000f) * 1.2f;

        int count = Math.round((scoreAdder / 2)) + 1;

        for (int i = 0; i < count; i++) {
            Enemy enemy = new Enemy(this, randomRange((int) (3 + scoreAdder), (int) (10 + scoreAdder)), anim);
            enemy.setCenterPivot(false);
            enemy.x = (int) this.getWidth() + enemy.width / 2;
            enemy.y = randomRange(0, (int) this.getHeight() - enemy.height / 2);
            enemies.add(enemy);
        }
    }

    private int randomRange(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private void UpdateCollision() {

    }

    private void UpdateExplosions() {
        for (int j = explosions.size() - 1; j >= 0; j--) {
            explosions.get(j).update();

            if (explosions.get(j).isActive == false) {
                explosions.get(j).destroy();
                explosions.remove(j);
            }
        }
    }

    private void AddExplosion(int x, int y) {
        Explosion ex = new Explosion(this, anim);
        ex.x = (int) (x + ex.getWidth() / 2);
        ex.y = y;
        explosions.add(ex);
    }

    private void CheckForEndGame() {
        if (player.Health <= 0) {
            player.Health = 100;
            this.game.gameScore = score;
            score = 0;
            gameHud.setLives(100);
            gameHud.setScore(0);

            this.musicSound.stop();

            this.player.y = game.getHeight() / 2;
            this.player.x = 0;

            isGameOver = true;
            this.game.scene = 2;
            this.game.over.reset();
        }
    }

}
