package com.maks362880.userservice.grpc;

import com.maks362880.userservice.model.User;
import com.maks362880.userservice.service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import user.UserServiceGrpc;

import java.time.Instant;
import java.time.ZoneOffset;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserService userService;

    @Override
    public void register(user.User.RegisterRequest request, StreamObserver<user.User.RegisterResponse> responseObserver) {
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        newUser.setEmail(request.getEmail());
        newUser.setRole(request.getRole());

        User registeredUser = userService.registerUser(newUser);

        user.User.RegisterResponse response = user.User.RegisterResponse.newBuilder()
                .setUserId(registeredUser.getId())
                .setUsername(registeredUser.getUsername())
                .setEmail(registeredUser.getEmail())
                .setStatusCode(user.User.StatusCode.SUCCESS)
                .setRegisteredAt(com.google.protobuf.Timestamp.newBuilder()
                        .setSeconds(registeredUser.getRegisteredAt().toEpochSecond(ZoneOffset.UTC))
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void login(user.User.LoginRequest request, StreamObserver<user.User.LoginResponse> responseObserver) {
        User requestUser = userService.loginUser(request.getUsername(), request.getPassword());

        user.User.LoginResponse response = user.User.LoginResponse.newBuilder()
                .setUserId(requestUser.getId())
                .setToken("generated-jwt-token") // Здесь можно добавить генерацию JWT токена
                .setUsername(requestUser.getUsername())
                .setStatusCode(user.User.StatusCode.SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(user.User.GetUserRequest request, StreamObserver<user.User.GetUserResponse> responseObserver) {
        User requestUser = userService.getUserById(request.getUserId());

        user.User.GetUserResponse response = user.User.GetUserResponse.newBuilder()
                .setUserId(requestUser.getId())
                .setUsername(requestUser.getUsername())
                .setEmail(requestUser.getEmail())
                .setRole(requestUser.getRole())
                .setStatusCode(user.User.StatusCode.SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}