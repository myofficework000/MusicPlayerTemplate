package com.code4galaxy.musicplayertemplate.data

import com.code4galaxy.musicplayertemplate.domain.model.User
import com.code4galaxy.musicplayertemplate.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth): AuthRepository {


    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: Flow<User?> = _currentUser

    init {
        auth.addAuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            _currentUser.value = firebaseUser?.let {
                User(
                    uid = it.uid,
                    email = it.email.orEmpty(),
                    isEmailVerified = it.isEmailVerified
                )
            }
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> = runCatching{
        auth.signInWithEmailAndPassword(email,password)
            .await()
        Unit
    }

    override suspend fun signup(
        email: String,
        password: String
    ): Result<Unit> = runCatching{
        auth.createUserWithEmailAndPassword(email,password).await()
        Unit
    }

    override suspend fun loginWithCredential(
        credential: AuthCredential
    ): Result<Unit> = runCatching {

        auth.signInWithCredential(credential)
            .await()

        Unit
    }

    override suspend fun logout() {
        auth.signOut()
    }


}