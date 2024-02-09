package fr.JO.jeuxOlympiques.exception;

public class EntitityNotFoundException extends RuntimeException{
    public EntitityNotFoundException() {
    }

    public EntitityNotFoundException(String message) {
        super(message);
    }
}
