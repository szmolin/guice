package com.google.inject;

import com.google.inject.spi.Message;

import java.util.Collection;

/**
 * Created by wyt on 16-10-24.
 */
public class FailFastException extends CreationException {

    public FailFastException(Collection<Message> messages) {
        super(messages);
    }
}
