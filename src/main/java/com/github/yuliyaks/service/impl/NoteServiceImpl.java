package com.github.yuliyaks.service.impl;

import lombok.RequiredArgsConstructor;
import com.github.yuliyaks.repository.NoteRepository;
import com.github.yuliyaks.model.Note;
import com.github.yuliyaks.service.NoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    // Создать заметку
    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    // Получить все заметки
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Получить заметку по ID
    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    // Обновить заметку
    @Override
    public Note updateNote(Note note) {
        Note noteById = getNoteById(note.getId());

        noteById.setTitle(note.getTitle());
        noteById.setContents(note.getContents());
        noteById.setCreationDate(LocalDateTime.now());

        return noteRepository.save(noteById);
    }

    // Удалить заметку
    @Override
    public void deleteNote(Long id) {
        Note noteById = getNoteById(id);
        noteRepository.delete(noteById);
    }

}
