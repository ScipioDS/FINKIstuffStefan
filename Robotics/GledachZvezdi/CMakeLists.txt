cmake_minimum_required(VERSION 3.27)
project(Kod_Za_Arduino)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)
include_directories(master)

add_executable(Kod_Za_Arduino
        master/.idea/.gitignore
        master/.idea/master.iml
        master/.idea/modules.xml
        master/.idea/workspace.xml
        master/master.ino
        slave.ino)
