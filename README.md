# Music Player Template

This is a basic Music Player template for Android using Jetpack Compose, Media3, and Hilt.

## Features
- Play/Pause music
- Progress tracking with Slider
- Sample track playback
- Hilt for Dependency Injection
- Media3 ExoPlayer for audio playback

## How to use the player
1.  **MusicPlayerWrapper**: This class wraps the `ExoPlayer` and provides a simple API for playing, pausing, and seeking. It also exposes state flows for playback state.
2.  **MusicPlayerViewModel**: Manages the playback state and interacts with `MusicPlayerWrapper`.
3.  **MusicPlayerScreen**: The UI component that displays the player controls and progress.
4.  **Playback**: To play a track, call `viewModel.play(url)`. The sample track button uses a publicly available MP3.

## Setup
The project uses Kotlin 2.1.0 and Version Catalog. Ensure you have the latest Android Studio installed.
