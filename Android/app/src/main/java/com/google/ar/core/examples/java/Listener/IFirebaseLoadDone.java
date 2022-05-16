package com.google.ar.core.examples.java.Listener;

import com.google.ar.core.examples.java.Model.Graffiti;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<Graffiti> graffitiList);
    void onFirebaseLoadFailed(String message);
}
