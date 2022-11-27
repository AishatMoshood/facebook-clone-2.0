package com.aishatmoshood.facebookclone.controllers;

import com.aishatmoshood.facebookclone.services.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;
}
