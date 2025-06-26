import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundPlayer {

    private Clip clip;

    public void playSound(String filepath, boolean loop) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                if(loop)
                    gainControl.setValue(-8.0f); 
                // System.out.println(gainControl.getMaximum());
            } else {
                System.out.println("MASTER_GAIN control not supported.");
            }

            clip.start();
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop BGM
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
