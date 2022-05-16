package com.guimsmendes.legoland.entrypoint.exception;

public class EntryPointException extends RuntimeException{

    EntryPointException(){}
    public static class ReadLineException extends RuntimeException {
        public ReadLineException(String message){
            super(message);
        }
    }
}
