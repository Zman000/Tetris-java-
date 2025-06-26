public class SoundManager {
    private static final SoundPlayer bgm = new SoundPlayer();
    private static final SoundPlayer sfx = new SoundPlayer();

    public static void playBGM(String file) {
        bgm.playSound(file, true);
    }

    public static void stopBGM() {
        bgm.stop();
    }

    public static void playSFX(String file) {
        sfx.playSound(file, false);
    }
}